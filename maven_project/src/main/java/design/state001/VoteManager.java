package design.state001;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 *   Title: 环境类  类似于Context 
 *     在状态模式中，环境(Context)是持有状态的对象，但是环境(Context)自身并不处理跟状态相关的行为，
 *     而是把处理状态的功能委托给了状态对应的状态处理类来处理。
            在具体的状态处理类中经常需要获取环境(Context)自身的数据，甚至在必要的时候会回调环境(Context)的方法，
            因此，通常将环境(Context)自身当作一个参数传递给具体的状态处理类。
            客户端一般只和环境(Context)交互。客户端可以用状态对象来配置一个环境(Context)，一旦配置完毕，
            就不再需要和状态对象打交道了。客户端通常不负责运行期间状态的维护，也不负责决定后续到底使用哪一个具体的状态处理对象。

 *   Description:
 * </pre>
 * 
 * @author haobin
 * @date 2017年12月25日 下午5:34:46
 * @version 1.0
 */
public class VoteManager {
	// 持有状体处理对象
	private VoteState state = null;
	// 记录用户投票的结果，Map<String,String>对应Map<用户名称，投票的选项>
	private Map<String, String> mapVote = new HashMap<String, String>();
	// 记录用户投票次数，Map<String,Integer>对应Map<用户名称，投票的次数>
	private Map<String, Integer> mapVoteCount = new HashMap<String, Integer>();

	/**
	 * 获取用户投票结果的Map
	 */
	public Map<String, String> getMapVote() {
		return mapVote;
	}

	/**
	 * 投票
	 * 
	 * @param user
	 *            投票人
	 * @param voteItem
	 *            投票的选项
	 */
	public void vote(String user, String voteItem) {
		// 1.为该用户增加投票次数
		// 从记录中取出该用户已有的投票次数
		Integer oldVoteCount = mapVoteCount.get(user);
		if (oldVoteCount == null) {
			oldVoteCount = 0;
		}
		oldVoteCount += 1;
		mapVoteCount.put(user, oldVoteCount);
		// 2.判断该用户的投票类型，就相当于判断对应的状态
		// 到底是正常投票、重复投票、恶意投票还是上黑名单的状态
		if (oldVoteCount == 1) {
			state = new NormalVoteState();
		}
		else if (oldVoteCount > 1 && oldVoteCount < 5) {
			state = new RepeatVoteState();
		}
		else if (oldVoteCount >= 5 && oldVoteCount < 8) {
			state = new SpiteVoteState();
		}
		else if (oldVoteCount > 8) {
			state = new BlackVoteState();
		}
		// 然后转调状态对象来进行相应的操作
		state.vote(user, voteItem, this);
	}
}
