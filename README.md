README
======

WIP

Requirements
============

Please note that this is the expectation :

# User should be able to provide a test file with
    * N orders and jit delay
        * OrderID, Area, City, (Lat, Lon), Schdule after, details
    * Should be able to persist in postgres.
* Seed DB with delivery boys
    * lat, lon, id, details
* Assignment cron
    * Should pick up order by area
    * Calculate temporal features for all valid DE
        * order delay
        * de idle time
        * first mile or routing time
    * Assign

Input - jar and text file
output - assignment (orderid, DE id)

Steps for executing the code
============================

* run all sql queries in migration.sql to setup the DB and seed it with Data.
* run the jar
