package com.neuq.info.dao;

import com.neuq.info.dto.Page;
import com.neuq.info.dto.ResultModel;
import com.neuq.info.entity.Post;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by lihang on 2017/4/2.
 */
public interface PostDao {
    /**
     * 新增一条post
     * @param post
     * @return
     */
    int insertPost(Post post);

    /**
     * 根据要求查询post
     * @param offset
     * @param limit
     * @return
     */
    List<Post> queryPostByCount(@Param("offset") int offset , @Param("limit") int limit);

    /**
     * 根据分页查询post
     * @param page
     * @return
     */
    List<Post> queryPostByPage(Page page);

    /**
     * 查询所有数量
     * @return
     */
    int queryAllPostCount();
    /**
     * 查询所有post
     * @return
     */
    List<Post> queryAllPost();

    /**
     * 删除post
     * @param postId
     * @return
     */
    int deletePost(long postId);

    /**
     * 更新post 点赞数量
     * @param postId
     * @param flag 1为增加点赞 0为取消点赞
     * @return
     */
    int updateLikeCount(@Param("postId") long postId,@Param("flag") int flag);

    /**
     * 更新post 评论数量
     * @param postId
     * @return
     */
    int updateCommentCount(long postId);

    List<Post> queryPostByUserId(long userId);

    Post queryPostByPostId(long postId);


}
