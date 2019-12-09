package org.ko.zookeeper.zkclient;

import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.junit.jupiter.api.Test;
import org.ko.zookeeper.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class ZkClientCrudTest {

    final static Logger logger = LoggerFactory.getLogger(ZkClientCrudTest.class);

    @Test
    void testZkClientCrud() {
        ZkClientCrud<User> zkClientCrud = new ZkClientCrud<>(new SerializableSerializer());
        String path="/root";
        zkClientCrud.deleteRecursive(path);
        zkClientCrud.createPersistent(path,"hi");
     /*  zkClientCrud.createPersistent(path+"/a/b/c",true);//递归创建 但是不能设在value
       //zkClientCrud.createPersistent(path,"hi");
        logger.info(zkClientCrud.readData(path));
        //更新
        zkClientCrud.writeData(path,"hello");
        logger.info(zkClientCrud.readData(path));
        logger.info(String.valueOf(zkClientCrud.getChildren(path)));
        //子节点
        List<String> list=zkClientCrud.getChildren(path);
        for(String child:list){
            logger.info("子节点:"+child);
        }*/

        User user = new User();
        user.setId(1);
        user.setName("K.O");
        user.setAge(18);
        user.setEmail("ko.shen@hotmail.com");
        zkClientCrud.writeData(path, user);
        System.out.println(zkClientCrud.readData(path).getName());;
    }


    @Test void testIsNumber() {
        System.out.println(isNumber("123a"));
    }

    private boolean isNumber(String n) {
        try {
            new BigDecimal(n);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}
