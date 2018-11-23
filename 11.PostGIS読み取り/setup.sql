CREATE TABLE sample_point (
  id SERIAL PRIMARY KEY
  , name text
  , geom GEOMETRY (POINT , 4326)
);

INSERT INTO sample_point(name,geom) VALUES
  ('wynkoop brewing company', ST_GeomFromText('POINT(39.7533 -104.9987)', 4326)),
  ('oxford hotel',            ST_GeomFromText('POINT(39.7522 -104.9992)', 4326)),
  ('magnolia hotel',          ST_GeomFromText('POINT(39.7467 -104.9922)', 4326)),
  ('contemporary art museum', ST_GeomFromText('POINT(39.7522 -105.0041)', 4326));
