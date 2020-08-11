#Base Image
FROM cassandra:latest

WORKDIR ./
EXPOSE 9042

CMD ["docker-entrypoint.sh", "cassandra"]