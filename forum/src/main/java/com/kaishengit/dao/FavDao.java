package com.kaishengit.dao;

import com.kaishengit.entity.Fav;
import com.kaishengit.util.DbHelp;
import org.apache.commons.dbutils.handlers.BeanHandler;

public class FavDao {
    public Fav findFavByUseridAndTopicid(Integer id, String topicid) {
        String sql = "select * from t_fav where userid = ? and topicid = ?";
        return DbHelp.query(sql,new BeanHandler<>(Fav.class),id,topicid);
    }

    public void addFav(Fav fav) {
        String sql = "insert into t_fav (userid,topicid) values (?,?)";
        DbHelp.update(sql,fav.getUserid(),fav.getTopicid());
    }

    public void deleteFav(Integer id, String topicid) {
        String sql = "delete from t_fav where userid = ? and topicid = ?";
        DbHelp.update(sql,id,Integer.valueOf(topicid));
    }
}
