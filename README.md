xrm-test
========
Тестовое задание №2 (http://xrm.ru/job/test_task/)

Решение поделено на три проекта:

1. version-annotation - аннотация и сканер, работающий с БД
2. example - JSF-приложение, которое якобы разрабатывается с использованием этой аннотации
3. build-versions - GWT-приложение, которым пользуется менеджер для просмотра информации об изменениях в проекте

Настройки БД находятся в двух местах: 
- version-annotation/src/main/resources/hibernate.cfg.xml
- build-versions/src/main/webapp/WEB-INF/applicationContext.xml

Для того, чтобы проверить задание нужно:

1. Выполнить mvn clean install в проекте version-annotation
2. Выполнить mvn clean process-classes -P dump в проекте example (можно даже собрать и запустить example)
3. Выполнить mvn clean package в проекте build-versions и развернуть полученный war-архив в любом сервлет-контейнере (лучше Tomcat 7). (альтернатива - mvn gwt:run для запуска в dev mode на jetty)

