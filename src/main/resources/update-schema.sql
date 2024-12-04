CREATE TABLE rpg.player
(
    id         BIGINT      NOT NULL,
    name       VARCHAR(12) NOT NULL,
    title      VARCHAR(30) NOT NULL,
    race       INT         NOT NULL,
    profession INT         NOT NULL,
    birthday   datetime    NOT NULL,
    banned     BIT(1)      NOT NULL,
    level      INT         NOT NULL,
    CONSTRAINT pk_player PRIMARY KEY (id)
);