
<h3>Описание:</h3>
 <h3> Архитектура</h3>
  <p>Здесь представлены и кратко описаны модули приложения.</p>
  <h2>Основные модули</h2>
  <ul>
        <li><b>order-api</b></li>
        <ul>
        <li>Предоставление списка продуктов и заказов</li>
        </ul>    
    </ul>
  </ul> 
  <h2>Вспомогательные модули</h2>
  <ul>
    <li><b>core</b></li>
    <ul>
    <li> содержит набор подмодулей, которые логически сгруппированы и содержат внутри себя набор классов, предназначенных для повторного использования, конфигурационные файлы и сторонние библиотеки, необходимые для интеграции со Spring Framework.</li>
    </ul>
    <li><b>oauth-db</b></li>
    <ul>
    <li>содержит набор классов для работы с БД, совместно используемых в модулях </li>
    </ul>
  </ul> 




<h3>Используемые технологии:</h3>

<h2>Backend main:</h2>
<ul>
   <li>Java 11</li>
   <li>Spring framework (spring boot)</li>
   <li>Postgresql</li>
   <li>Hibernate</li>
</ul>

<h2>Backend test:</h2>
<ul>
   <li>Java 11</li>
   <li>jUnit 4</li>
   <li>Mockito</li>
</ul>

 <h2>Frontend:</h2>
<ul>
   <li>Vue js</li>
   <li>CSS</li>
   <li>Thymeleaf</li>
   <li>Bootstrap</li>
</ul>
  
<h3>Использование</h3>
   <p> Файл с продуктами находится в корне проекта</p>
  <p>Создать форму базу данных по скрипту:  CREATE DATABASE orders; </p>
  <p>Далее с помощью Maven собрать War файлы  запустив функцию install в Lifecycle как выбрано на рисунке и запустить OrdersApiApplication</p>
  <p>https://github.com/Slavko13/orders-test-app/issues/1#issue-693231666</p>
  ![image](https://user-images.githubusercontent.com/48677713/92251045-eb841200-eed4-11ea-8045-a10272bfc410.png)
  <p> Далее перейти на localhost:8081/ , на данной странице будет доступны 2 формы и кнопочка обновить список товаров</p>
  <p> Форма1: </p>
  <p> Список товаров с кнопочкой "Добавить в заказ"</p>
  <p> Форма2: </p>
  <p> Форма заказа </p>
  
