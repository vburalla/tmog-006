package org.tfoc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class CodecTest {

    @Test
    void testCodec() {

        log.info("Don't forget to write the tests to make sure it works. Enjoy!");
    }

    @Test
    void serialize() {
    }

    @Test
    void deserialize() {
        String text = "[1,2,3,null,null,4,5]";

        Codec codec = new Codec();
        TreeNode node = codec.deserialize(text);
        String ouputText = codec.serialize(node);

        assertEquals(text, ouputText);
    }

}