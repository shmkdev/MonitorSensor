--changeset ramanshmk:1
CREATE TABLE IF NOT EXISTS sensor_stats (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    stat_date TIMESTAMP NOT NULL,
    total_sensors INT NOT NULL,
    type VARCHAR(255),
    count_by_type INT NOT NULL
    );