# Setup

The game didn't work when I run it from docker compose (hangs on initial user input: number of players), the solution was to make the Dockerfile build the game only then give the user access to the shell where we can run the game inside the container without issues.

* build: `docker compose up --build`
* run: `docker compose run uno-game-cli`

If you happen to see this warning, add `--remove-orphans` to the build command to fix it as recommended:

```
WARN[0000] Found orphan containers ([uno-card-game-cli-version-uno-game-cli-run-3613ab29787e uno-card-game-cli-version-uno-game-cli-run-13dfa80f8880 uno-card-game-cli-version-uno-game-cli-run-1dd850cf6ae1]) for this project. If you removed or renamed this service in your compose file, you can run this command with the --remove-orphans flag to clean it up.
```

Inside the container (you should have an output like the following):

```
> docker compose run uno-game-cli
root@a0b617519794:/app# 
```

* run: `./run.sh`

Once running:
* <kbd>ctrl</kbd> + <kbd>c</kbd>: stop the game
* <kbd>ctrl</kbd> + <kbd>d</kbd>: exit the container
