package com.dgf.shopproduct.service;

import com.dgf.shopproduct.Constants;
import com.dgf.shopproduct.model.Product;
import com.dgf.shopproduct.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepo repo;

    public Flux<Product> findByNameIgnoreCaseContaining(String name) {
        log.info("finding names like: {}", name);
        return repo.findByNameIgnoreCaseContaining(name);
    }

    public Flux<Product> create(List<Product> items) {
        return repo.deleteAll().thenMany(
                repo.saveAll(items)
        );
    }

//    Flux<Product> increaseStock(List<String> items) {
//        log.info("increase stock {} ", items);
//        Flux<Product> updatedItems = repo.findAllById(items)
//            .map(Product::availabilityInc);
//        log.info("increase stock save updatedItems={} ", updatedItems);
//        return repo.saveAll(updatedItems);
//    }

//    Flux<Product> decreaseStock(@NotNull List<String> items) {
//        log.info("decrease stock {} ", items);
//        Flux<Product> availableItems = repo.findAllById(items).map(Product::availabilityDec)
//            .filter(Optional::isPresent)
//            .map(Optional::get);
//        log.info("decrease stock save availableItems={} ", availableItems);
//        return repo.saveAll(availableItems);
//    }

    public Flux<Product> findAll() {
        return repo.findAll(Sort.by("name"));
    }

    @PostConstruct
    public void initData() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                boolean repeat;
                int repeatCount=0;
                do {
                    repeat=false;
                    try {
                        //data is pre-filled with mongo-insert.json
                        if (repo.findAll().collectList()
                                .blockOptional()
                                .orElseThrow(() -> new RuntimeException("MongoDb not ready (findAll)"))
                                .size()==0)
                            initDataInThread(repeatCount);
                    } catch (RuntimeException e) {
                        repeat = true;
                        log.error("MongoDb not ready repeatCount #"+repeatCount, e);
                    }
                    if (repeat) {
                        repeatCount++;
                        try {
                            log.info("waiting for mongo to get started... (repeat count={})", repeatCount);
                            sleep(7000);
                        } catch (InterruptedException e) {
                            log.error("", e);
                        }
                    } else log.info("InitData creation OK!!! (repeat count={})", repeatCount);
                } while (repeat && repeatCount<100);
            }
        }.start();
    }

    private void initDataInThread(int repeatCount) {
        log.info("Creating products");
        repo.saveAll(Constants.PRODUCTS.get()).subscribe();
        checkMongoStatus();
        log.info("Creating products... OK!!");
    }

    private void checkMongoStatus() {
        log.info("Checking mongo status...");
        List<Product> products = repo.findAll()
                .collectList()
                .blockOptional()
                .orElseThrow(() -> new RuntimeException("MongoDb not ready (findAll)"));
        if (products.size()>Constants.PRODUCTS.get().size())
            repo.deleteAll().subscribe(); //todo as ids change if product-pod is redeployed must deleteAll to create non duplicate products (see Constants.PRODUCTS)
        if (products.size()!=Constants.PRODUCTS.get().size())
            throw new RuntimeException("MongoDb not ready (findAll size mismatch)");
        log.info("Checking mongo status... OK!!");
    }

    public Flux<Product> insertAll() {
        return repo.saveAll(Constants.PRODUCTS.get());
    }
}