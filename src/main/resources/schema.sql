CREATE TABLE customer (
    id         INTEGER      NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(128) NOT NULL,
    last_name  VARCHAR(128) NOT NULL,
    address    VARCHAR(128) NULL,
    email      VARCHAR(128) NOT NULL,
    phone      VARCHAR(128) NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);