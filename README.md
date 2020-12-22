#Simple API test

## Run via Idea
1. Before run tests start a service (check docker demon is running):
``docker run -p 8091:8091/tcp azshoo/alaska``
1. Open ``reference.conf`` and switch to ```//alaskaservice.baseuri = "http://0.0.0.0:8091" // local run```
1. Run test Intellij Idea or etc
1. Run ``mvn allure:serve`` to get a report

## Run via docker-compose
1. From /alaska-qa build image with tests: ``docker build -t alaska-tests .``
1. Run `docker-compose.yml`
1. Check a directory ``/tmp/alaska-tests`` to open allure report
