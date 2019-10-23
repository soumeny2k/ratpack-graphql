FROM docker.target.com/tap/alpine-jre

RUN wget -O /usr/local/bin/dumb-init https://binrepo.target.com/artifactory/cpe-yum-source/up-agent-tools/dumb-init_1.2.2_amd64 && \
	chmod u+x /usr/local/bin/dumb-init

ADD build/distributions/ratpacklearning.tar /

ENTRYPOINT ["/usr/local/bin/dumb-init", "--", "/ratpacklearning/bin/ratpacklearning"]