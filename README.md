# Description
In Russia, telegram API is blocked. Public proxies always changed and i don't believe them. So i create this mini project with 
heroku for resolve it problem. What you need do for startup it project on heroku:
* Create a telegram bot via @botfather
* Save bot's $API_KEY
* Send message to your new bot for creating $CHAT_ID
* Register on [Heroku](http://heroku.com/)
* Install [heroku CLI](https://devcenter.heroku.com/articles/getting-started-with-java#set-up)
* Clone this repository
```
git clone git@github.com:alexeyvip/telegram-bot.git
cd telegram-bot
 ```
* Create app in heroku
``` 
heroku create
```
* Set up $API_KEY from step above via "heroku config"
```
heroku config:set TELEGRAM_API_KEY=$API_KEY
```
* Deploy project
```
git push heroku master
```
* Ensure that at least one instance of the app is running
```
heroku ps:scale web=1
```
* Open in browser "$HEROKU_URL"(was generated in "Create app in heroku")/getUpdates and you see JSON, you should save chat_id
* Set up $CHAT_ID from above step via "heroku config"
```
heroku config:set TELEGRAM_CHAT_ID=$CHAT_ID
```
* Restart your app on heroku
* Now, you can send telegram message via
```
curl -G -v --data-urlencode "text=Hey" "https://$$HEROKU_URL/message/send"
``` 
