package com.zhj.study.frameworks.redis.material.hongbao;

import java.util.List;

import redis.clients.jedis.Jedis;

/**
 * 
 * 
 *
 * <pre>
 * 修改日期		修改人	修改原因
 * 2016-12-20	钟华杰	新建
 * </pre>
 */
public class ScrambleHongBao {

	/**
	 * @param args
	 *
	 * <pre>
	 * 修改日期		修改人	修改原因
	 * 2016-12-20	钟华杰	新建
	 * </pre>
	 */
	public static void main(String[] args) {
		// 资源准备
		// 测试竞争
		
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		jedis.auth("081181001zhj");
		System.out.println(jedis.ping());
		System.out.println(jedis.scriptLoad("return {KEY[1], KEY[2], ARGV[1], ARVG[2]}"));
		//f33a61072ca8d3e0cc4f6b7080602f4edbce9556
		//f33a61072ca8d3e0cc4f6b7080602f4edbce9556
		System.out.println(jedis.scriptExists("f33a61072ca8d3e0cc4f6b7080602f4edbce9556"));
	}
	
	public static void initData(double totalMoney) {
		OriginalHongBao originalHongBao = new OriginalHongBao(totalMoney);
	}
	
	public static void startScramble() {
		
	}
	
	private static List<HongBao> departOriginalHongBao(OriginalHongBao originalHongBao, int count) {
		int i = 100000;
		return null;
	}
	
	public static class OriginalHongBao {
		private double totalMoney;
		
		public OriginalHongBao(double totalMoney) {
			this.totalMoney = totalMoney;
		}
	}
	
	public static class HongBao {
		private String owner;
		private double money;
		/**
		 * @return the owner
		 */
		public String getOwner() {
			return owner;
		}
		/**
		 * @param owner the owner to set
		 */
		public void setOwner(String owner) {
			this.owner = owner;
		}
		/**
		 * @return the money
		 */
		public double getMoney() {
			return money;
		}
		/**
		 * @param money the money to set
		 */
		public void setMoney(double money) {
			this.money = money;
		}
	}
}
