package io.midway.payloads;

public class VotesPayloads {

    public String postVote() {

        String imageId = "asf2";
        String subId = "my-user-1234";
        String payload = "{\n" +
                "    \"image_id\": \"" + imageId + "\",\n" +
                "    \"sub_id\": \"" + subId + "\",\n" +
                "    \"value\": 1\n" +
                "}";

        return payload;
    }
}
