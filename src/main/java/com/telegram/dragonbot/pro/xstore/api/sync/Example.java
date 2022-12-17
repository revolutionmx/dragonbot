package com.telegram.dragonbot.pro.xstore.api.sync;

import com.telegram.dragonbot.pro.xstore.api.message.codes.TRADE_OPERATION_CODE;
import com.telegram.dragonbot.pro.xstore.api.message.codes.TRADE_TRANSACTION_TYPE;
import com.telegram.dragonbot.pro.xstore.api.message.command.APICommandFactory;
import com.telegram.dragonbot.pro.xstore.api.message.records.STickRecord;
import com.telegram.dragonbot.pro.xstore.api.message.records.STradeRecord;
import com.telegram.dragonbot.pro.xstore.api.message.records.TickRecord;
import com.telegram.dragonbot.pro.xstore.api.message.records.TradeTransInfoRecord;
import com.telegram.dragonbot.pro.xstore.api.message.response.LoginResponse;
import com.telegram.dragonbot.pro.xstore.api.message.response.SymbolResponse;
import com.telegram.dragonbot.pro.xstore.api.message.response.TickPricesResponse;
import com.telegram.dragonbot.pro.xstore.api.message.response.TradeTransactionResponse;
import com.telegram.dragonbot.pro.xstore.api.streaming.CandlesSubscribe;
import com.telegram.dragonbot.pro.xstore.api.streaming.StreamingListener;
import com.telegram.dragonbot.pro.xstore.api.sync.ServerData.ServerEnum;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Map;


public class Example {

	public void runExample(ServerEnum server, Credentials credentials) throws Exception {
		try {
			SyncAPIConnector connector = new SyncAPIConnector(server);
			LoginResponse loginResponse = APICommandFactory.executeLoginCommand(connector, credentials);
			System.out.println(loginResponse);
			if (loginResponse != null && loginResponse.getStatus())
			{
				StreamingListener sl = new StreamingListener() {
					@Override
					public void receiveTradeRecord(STradeRecord tradeRecord) {
						System.out.println("Stream trade record: " + tradeRecord);
					}
	
					@Override
					public void receiveTickRecord(STickRecord tickRecord) {
						System.out.print("Stream tick record: " + tickRecord);
					}
				};

				LinkedList<String> list = new LinkedList<String>();
				String symbol = "DE30";
				list.add(symbol);

				TickPricesResponse resp = APICommandFactory.executeTickPricesCommand(connector, 0L, list, 0L);
				for (TickRecord tr : resp.getTicks()) {
					System.out.println("TickPrices result: "+tr.getSymbol() + " - ask: " + tr.getAsk());
				}

				connector.connectStream(sl);
				System.out.println("Stream connected.");
				
				connector.subscribePrice(symbol);
				connector.subscribeTrades();
	
				Thread.sleep(10000);

				connector.unsubscribePrice(symbol);
				connector.unsubscribeTrades();
			
				connector.disconnectStream();
				System.out.println("Stream disconnected.");
				
				Thread.sleep(5000);
				
				connector.connectStream(sl);
				System.out.println("Stream connected again.");
				connector.disconnectStream();
				System.out.println("Stream disconnected again.");
				System.exit(0);
			}
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}
	public void runExample2(ServerEnum server, Credentials credentials,String symbolReq,double tpReq,double slReq,double apertura,Integer operazione) throws Exception {
		try {
			SyncAPIConnector connector = new SyncAPIConnector(server);
			LoginResponse loginResponse = APICommandFactory.executeLoginCommand(connector, credentials);
			System.out.println(loginResponse);
			if (loginResponse != null && loginResponse.getStatus())
			{
				StreamingListener sl1 = new StreamingListener() {
					@Override
					public void receiveTradeRecord(STradeRecord tradeRecord) {
						System.out.println("Stream trade record: " + tradeRecord);
					}

					@Override
					public void receiveTickRecord(STickRecord tickRecord) {
						System.out.print("Stream tick record: " + tickRecord);
					}
				};




				SymbolResponse symbolResponse = APICommandFactory.executeSymbolCommand(connector, symbolReq);


				//double price = apertura;
				double price = symbolResponse.getSymbol().getAsk();
				double sl = slReq;
				double tp = tpReq;
				String  symbol = symbolResponse.getSymbol().getSymbol();
				double volume = 0.01;
				long order = 0;
				String customComment = "my comment";
				long expiration = 0;
				TradeTransInfoRecord ttOpenInfoRecord = null;
				switch (operazione) {
					case 0:
						 ttOpenInfoRecord = new TradeTransInfoRecord(
								TRADE_OPERATION_CODE.SELL,
								TRADE_TRANSACTION_TYPE.OPEN,
								price, sl, tp, symbol, volume, order, customComment, expiration);
						break;
					case 1:
						 ttOpenInfoRecord = new TradeTransInfoRecord(
								TRADE_OPERATION_CODE.BUY,
								TRADE_TRANSACTION_TYPE.OPEN,
								price, sl, tp, symbol, volume, order, customComment, expiration);
						break;
				}
				/*TradeTransInfoRecord ttOpenInfoRecord = new TradeTransInfoRecord(
						TRADE_OPERATION_CODE.SELL_STOP,
						TRADE_TRANSACTION_TYPE.OPEN,
						price, sl, tp, symbol, volume, order, customComment, expiration);*/
				TradeTransactionResponse tradeTransactionResponse = APICommandFactory.executeTradeTransactionCommand(connector, ttOpenInfoRecord);


				/*connector.connectStream(sl1);
				System.out.println("Stream connected.");

				connector.subscribePrice(symbol);
				connector.subscribeTrades();

				Thread.sleep(10000);

				connector.unsubscribePrice(symbol);
				connector.unsubscribeTrades();

				connector.disconnectStream();
				System.out.println("Stream disconnected.");

				Thread.sleep(5000);

				connector.connectStream(sl1);
				System.out.println("Stream connected again.");
				connector.disconnectStream();
				System.out.println("Stream disconnected again.");
				System.exit(0);*/
			}
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}

	protected Map<String,Server> getAvailableServers() {
		return ServerData.getProductionServers();
	}
}