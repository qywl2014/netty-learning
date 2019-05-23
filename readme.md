```
任务1:AbstractChannel:new Runnable() {
                        @Override
                        public void run() {
                            register0(promise);//pipeline.invokeHandlerAddedIfNeeded();调用ChannelHandler的handlerAdded(),ChanelInizializer是调用initChannel
                        }
                    }
任务2:ServerBootstrap:new Runnable() {
                    @Override
                    public void run() {
                        pipeline.addLast(new ServerBootstrapAcceptor(
                                currentChildGroup, currentChildHandler, currentChildOptions, currentChildAttrs));
                    }
                }
任务3:AbstractBootstrap:new Runnable() {
            @Override
            public void run() {
                if (regFuture.isSuccess()) {
                    channel.bind(localAddress, promise).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);//线程池加入任务4，应该是
                } else {
                    promise.setFailure(regFuture.cause());
                }
            }
        }
任务4:AbstractChannel:new Runnable() {
                    @Override
                    public void run() {
                        pipeline.fireChannelActive();//线程池加入任务5
                    }
                }
任务5:AbstractOioChannel:new Runnable() {
        @Override
        public void run() {
            if (!isReadPending() && !config().isAutoRead()) {
                // ChannelConfig.setAutoRead(false) was called in the meantime so just return
                return;
            }

            setReadPending(false);
            doRead();
        }
    };
```