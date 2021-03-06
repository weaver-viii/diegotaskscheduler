# Diego Task Scheduler

This is a prototype task scheduling system that allows you to send docker images hosted on e.g. [Docker Hub](https://hub.docker.com/) to a running instance of [Lattice](http://lattice.cf/), or anything running the [Diego Receptor API](https://github.com/cloudfoundry-incubator/receptor/blob/master/doc/README.md), for that matter.

## Installation

As this is a [Clojure](http://clojure.org/) / [Leiningen](http://leiningen.org/) project, you need Leiningen installed. This can be achieved on a Mac with [Homebrew](http://brew.sh/) like so:

```sh
brew update
brew install leiningen
```

## Running locally

From the project directory, run the following to compile JavaScript:

```sh
lein repl
```

The first time you run this, Leiningen will download Clojure, ClojureScript and all the dependencies required of this project. It may take some time. When you get a prompt:

```clojure
(production-build)
(exit)
```

Note you can also do (development-build). See dev_src/user.clj for build differences.

Then run the following to compile a JAR:

```sh
lein uberjar
```

Now run the following to start the server:

```sh
PORT=8080 \
API_URL=http://192.168.11.11:8888/v1 \
WS_URL=ws://localhost:8080/ws \
java -jar target/diegoscheduler-0.2.0-SNAPSHOT-standalone.jar
```

Open a browser at [http://localhost:8080/](http://localhost:8080/). You should see a rudimentary interface for creating a Task. The defaults will result in a "Successful" docker image being downloaded and run. It will have error output in the Result column. This is because AWS_ACCESS_KEY_ID and AWS_SECRET_ACCESS_KEY have bogus values. You could replace these bogus values to have the s3copier task carry out its business properly.

## Running on CloudFoundry e.g. [PWS](https://run.pivotal.io/)

A manifest.yml is prepared for you in the repo. You just need to build the JAR as above, then do a:

```sh
cf push -n mysubdomain
cf set-env taskscheduler API_URL=http://receptor.123.123.123.123.xip.io/v1
cf set-env taskscheduler WS_URL=wss://mysubdomain.cfapps.io:4443/ws
cf restage taskscheduler
```

Where 123.123.123.123 is the IP of your lattice brain.

http://mysubdomain.cfapps.io should now be running.
