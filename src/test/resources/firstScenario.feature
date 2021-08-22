#language: ru

@all
Функционал: Поиск iphone

  @firstTest
  Сценарий: Поиск iphone на сайте ozon
    * Ищем товар "iphone"
    * Ограничеваем максимальную цену "100000"
    * Выбираем высокий рейтинг
    * Выбираем беспроводые интерфейсы:
      | NFC | true |
    * Выбираем "8" четных товаров
    * Переходим в корзину
    * Проверяем количество товаров в корзине
    * Проверяем что в корзине те товары
    * Удаляем все товары из корзины
    * Проверяем что корзина пуста

    



