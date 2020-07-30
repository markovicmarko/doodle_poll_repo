#! /bin/bash

mongoimport --host mongocontainer -u root -p rootMM --db doodle_poll_db --collection poll --type json --file /mongo-seed/polls.json --jsonArray