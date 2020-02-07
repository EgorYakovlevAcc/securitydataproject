package project.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import project.coder.HashExtension;
import project.coder.HashFunction;
import project.exception.NoHashFunctionException;
import project.persistence.model.CalculateHashBody;
import project.persistence.model.ExtensionType;
import project.persistence.model.HashFunctionType;

import java.util.List;

@Service
@Slf4j
public class BackendServiceImpl {

    private final HashExtension hashExtension;
    private ApplicationContext applicationContext;

    public BackendServiceImpl(HashExtension hashExtension,
                              ApplicationContext applicationContext) {
        this.hashExtension = hashExtension;
        this.applicationContext = applicationContext;
    }

    public List<String> getFunctions() {
        return HashFunctionType.getAllHashFunctions();
    }

    public List<String> getExtensions() {
        return ExtensionType.getAllExtensions();
    }

    public String calculateHash(CalculateHashBody requestBody) {
        HashFunction function = resolveHashFunctions(requestBody);
        requestBody.setMessage(hashExtension.calculateExtensions(function, requestBody.getMessage().getBytes(), requestBody.getExtensions()));
        startAttacks(requestBody);
        return requestBody.getMessage();
    }

    @Async
    public void startAttacks(CalculateHashBody requestBody) {
        // ATACK
    }

    private HashFunction resolveHashFunctions(CalculateHashBody requestBody) {
        return applicationContext.getBeansOfType(HashFunction.class).values().stream()
                .filter(hashFunction -> hashFunction.getId().equals(requestBody.getHashFunction()))
                .findFirst()
                .orElseThrow(NoHashFunctionException::new);
    }
}
