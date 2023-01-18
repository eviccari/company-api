docker build -t company-api . && \
    docker image rm -f $(docker images -f dangling=true -q)