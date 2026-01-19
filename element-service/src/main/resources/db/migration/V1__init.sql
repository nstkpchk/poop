CREATE TABLE simplified_mountains (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE trails (
    id UUID PRIMARY KEY,
    trail_name VARCHAR(255),
    trail_length DOUBLE PRECISION,
    trail_difficulty VARCHAR(255),
    mountain_id UUID NOT NULL,
    CONSTRAINT fk_mountain FOREIGN KEY (mountain_id) REFERENCES simplified_mountains(id)
);