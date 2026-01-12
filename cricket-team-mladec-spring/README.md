
# Cricket Team Selection — Plain Spring + JDBC (mladec) — FIXED

This build removes the ambiguous lambda by explicitly casting to `ResultSetExtractor<Void>` in `JdbcTemplate.query(...)` calls.

## Run
mvn -q -DskipTests=true exec:java -Dexec.mainClass=com.mladec.cricketteam.App
