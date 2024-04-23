FROM maven:3.9.0

RUN mkdir -p /home/olga2/ui_tests

WORKDIR /home/olga2/ui_tests

COPY . /home/olga2/ui_tests

ENTRYPOINT ["/bin/bash", "entrypoint.sh"]