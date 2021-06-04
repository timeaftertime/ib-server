package cn.milai.ibserver.landvsair;

import java.util.List;

import org.springframework.stereotype.Component;

import cn.milai.ib.container.BaseDramaContainerFactory;
import cn.milai.ib.container.Container;
import cn.milai.ib.container.plugin.ContainerPlugin;

/**
 * 服务端 {@link Container} 工厂类
 * @author milai
 * @date 2021.05.22
 */
@Component
public class ServerContainerFactory extends BaseDramaContainerFactory {

	public ServerContainerFactory(List<ContainerPlugin> plugins) {
		super(plugins);
	}

	@Override
	public boolean isSingleton() { return false; }

}
