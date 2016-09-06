update location set centroid = ST_Point(longitude, latitude) ;


insert into location_geometry (id, geometry,location_id) select nextval('hibernate_sequence'), geom ,locationid from  region_geometry;
insert into location_geometry (id, geometry,location_id) select nextval('hibernate_sequence'), geom, locationid  from  province_geometry;
insert into location_geometry (id, geometry,location_id) select nextval('hibernate_sequence'), geom ,locationid  from  municipality_geometry;

update   location set location_geometry_id=g.id  from  location_geometry g  where location.id=g.location_id;