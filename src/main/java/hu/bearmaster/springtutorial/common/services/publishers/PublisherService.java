package hu.bearmaster.springtutorial.common.services.publishers;

import hu.bearmaster.springtutorial.common.model.Post;

public interface PublisherService {

    PublisherService NO_OP = post -> {};

    void notifyUsers(Post post);

}
