# UTH Support Backend

## Hướng dẫn chạy dev API

1. Cài đặt [JDK 21](https://adoptium.net/temurin/releases/?os=windows&arch=x64&package=jdk).
2. Cài [Docker](https://www.docker.com/).
3. Build image docker:
    ```shell
    ./gradlew bootBuildImage
    ```
   
4. Khởi động docker-compose:

   ```shell
   docker compose up
   ```
5. Truy cập http://localhost:8080/swagger-ui/index.html để test api.
