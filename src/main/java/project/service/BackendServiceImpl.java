package project.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class BackendServiceImpl {

    public List<String> getFunctions() {
        return Arrays.asList("MD5", "MD5_256", "SHA1");
    }

    public List<String> getExtensions() {
        return Arrays.asList("Extens1", "Extens2");
    }

    public String calculateHash() {
        return "1421h4i1u2";
    }
}
