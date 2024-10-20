# Troubleshooting

To debug this project, make sure to bind the current directory to the container's working directory (uncomment the `volumes` section in the `docker-compose.yml` file), this will make the artifacts generated withing the container show up in your host machine as well.

By default, Docker containers run as the `root` user unless explicitly specified otherwise. You can update the Dockerfile and pass the `USER_ID` and `GROUP_ID` during the build process but I chose the easier approach to change file ownership after creation.

```
sudo chown -R ${USER_ID}:${GROUP_ID} out
```

e.g.
```
sudo chown -R stoufa:stoufa out
```

> 💡 You can find the `USER_ID` and `GROUP_ID` of the current user in a Unix-based system (like Linux or macOS) using the `id` command, the output will look like:
> ```
> uid=1000(yourusername) gid=1000(yourgroup) groups=1000(yourgroup),...
> ```
> uid=1000 is your `USER_ID`.  
> gid=1000 is your `GROUP_ID`.  
>
> or simply using the command `echo $(id -u) $(id -g)`  
> This will output only the numerical IDs: e.g. `1000 1000`
