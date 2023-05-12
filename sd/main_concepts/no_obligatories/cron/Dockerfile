FROM jenkins/jenkins:2.387.3-lts

user root 
#Install Cron
RUN apt-get update
RUN apt-get -y install cron
RUN curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip" && \
    unzip awscliv2.zip && \
    ./aws/install

RUN echo "* * * * * /opt/APPLICATION/cmd.sh > /proc/1/fd/1 2>&1" >> /etc/cron.d/restart-cron \
    # Give the necessary rights to the user to run the cron
    && crontab -u jenkins /etc/cron.d/restart-cron \
    && chmod u+s /usr/sbin/cron

WORKDIR /opt/APPLICATION

COPY entrypoint.sh /opt/APPLICATION/entrypoint.sh
COPY cmd.sh /opt/APPLICATION/cmd.sh
RUN chown jenkins /opt/APPLICATION/entrypoint.sh
RUN chown jenkins /opt/APPLICATION/cmd.sh
RUN chmod +x /opt/APPLICATION/entrypoint.sh
RUN chmod +x /opt/APPLICATION/cmd.sh

user jenkins


ENTRYPOINT [ "./entrypoint.sh" ]
