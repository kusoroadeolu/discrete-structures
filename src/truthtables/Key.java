package truthtables;

import java.nio.charset.StandardCharsets;

public record Key(
        String key
) {

    public byte[] keyBytes() {
        return key.getBytes(StandardCharsets.UTF_8);
    }


}
