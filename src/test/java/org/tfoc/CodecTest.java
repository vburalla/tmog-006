package org.tfoc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class CodecTest {

    Codec codec = new Codec();

    @Test
    void deserialize() {
        String text = "[1,2,3,null,null,4,5]";

        TreeNode node = codec.deserialize(text);
        String ouputText = codec.serialize(node);

        assertEquals(text, ouputText);
    }

    @ParameterizedTest
    @ValueSource(strings = {"[1,2,3,null,null,4,5]", "[]"})
    void testCodec(String inputText) {

        TreeNode node = codec.deserialize(inputText);
        String ouputText = codec.serialize(node);

        assertEquals(inputText, ouputText);
    }

}