mysql --verbose --database=archguard --user=root -e "source /Users/ygdong/git/code-scanners/scan_jacoco/src/main/resources/db/migration/ddl.sql"
mysql --database=archguard --user=root -e "source /Users/ygdong/git/code-scanners/scan_jacoco/output.sql"