README
======

The project aims at calculating the cost of assignment (transportation) using various features based on business requirements. It further optimizes the cost using an Assignment Strategy. Hungarian Algorithm is used in the implemented startegy

Feature Cost Calculation
------------------------

Feature cost calculation has been kept configurable and is easily extendable by implementing the either SingleEntityFeature or MultipleEntityFeature interfaces and annotating them with appropriate annotations (For not it has to be added manually in feature calculator, till the time we make the annotations work). The cost, including the new feature will be calculated automatically without any further code changes.

Assignment Strategy
-------------------

Assignment Strategy is a generic interface used for assignment. Right now hungarian algorithm is used for assignment. The code adds dummy values (very large M) for all impossible assingments. A new assignment strategy can be easily added.   

Testing
=======

* Update DB details in /ordering/src/main/java/config
* Create tables and seed data by running /ordering/src/main/resources/db/migration.sql
* Run the main function. It will output the assignment for the seeded data
* Individual modules with core logic can be tested by running unit tests and funcitonal tests

TODO
====

* Better and more comprehensive testing
* Adding ORM
* Annotation based feature addition
