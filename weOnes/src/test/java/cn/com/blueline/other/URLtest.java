package cn.com.blueline.other;

import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import cn.com.blueline.utils.CommonUtils;

public class URLtest {

	private static int thread_num = 2000;
	private static int client_num = 50000;

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		final Semaphore semp = new Semaphore(thread_num);
		for (int index = 0; index < client_num; index++) {
			final int NO = index;
			Runnable run = new Runnable() {
				public void run() {
					try {
						semp.acquire();
						String url = "http://www.blueline.com.cn/";
						System.out.println("Thread:" + NO+"请求"+url);
						// 业务逻辑
						InputStream is = CommonUtils.doHttpGetRequest(url, "");
						String result = CommonUtils.convertStreamToString(is);
						System.out.println(result);
						semp.release();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			exec.execute(run);
		}
		exec.shutdown();
	}

}
