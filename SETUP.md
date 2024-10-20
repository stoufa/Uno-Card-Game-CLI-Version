# Setup

the game didn't work when I run it from docker compose (hangs on initial user input: number of players), the solution was to make the Dockerfile build the game only then give the user access to the shell where we can run the game inside the container without issues.

* build: `docker compose up --build`
* run: `docker compose run uno-game`

inside the container (you should have an output like the following):

```
> docker compose run uno-game
root@a0b617519794:/app# 
```

* run: `./run.sh`

once running:
* <kbd>ctrl</kbd> + <kbd>c</kbd>: stop the game
* <kbd>ctrl</kbd> + <kbd>d</kbd>: exit the container
