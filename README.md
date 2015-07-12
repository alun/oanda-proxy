
# oanda-proxy

This software provides an HTTP gateway which will add your credentials to any
call to the [Oanda REST API](http://developer.oanda.com/rest-live/introduction/).

[Authentication](http://developer.oanda.com/rest-live/authentication/) is done by simple adding a header to your call.

This reverse proxy will add the header to any of your calls.

## How to try

Simplest way is just to use it:

```
curl dev.katlex.com:3001/v1/accounts
```

## How to run your own

Its proposed to use [the docker image](https://registry.hub.docker.com/u/alexlun/oanda-proxy/).
But of course you could run it with `lein` as well.
