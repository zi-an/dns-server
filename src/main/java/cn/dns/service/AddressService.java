package cn.dns.service;

import cn.dns.dao.AddressMapper;
import cn.dns.entity.Address;
import cn.dns.entity.AddressExample;
import cn.dns.entity.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by matrixy on 2019/4/28.
 */
@Service
public class AddressService
{
    @Autowired
    AddressMapper addrMapper;

    public Long create(Address addr)
    {
        addrMapper.insert(addr);
        return addr.getId();
    }

    public int update(Address addr)
    {
        return addrMapper.updateByPrimaryKey(addr);
    }

    public int remove(Address addr)
    {
        return addrMapper.deleteByPrimaryKey(addr.getId());
    }

    public List<Address> find(Long ruleId)
    {
        return addrMapper.selectByExample(new AddressExample().createCriteria().andRuleIdEqualTo(ruleId).example());
    }

    public int removeByRule(Rule rule)
    {
        return addrMapper.deleteByExample(new AddressExample().createCriteria().andRuleIdEqualTo(rule.getId()).example());
    }
}
