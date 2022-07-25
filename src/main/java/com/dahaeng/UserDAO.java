import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public void insertBoard(BoardVO vo) {
        System.out.println("===> JPA로 insertBoard() 기능 처리");
        entityManager.persist(vo);
    }

    public void updateBoard(BoardVO vo) {
        System.out.println("===> JPA로 updateBoard() 기능 처리");
        entityManager.merge(vo);
    }

    public void deleteBoard(BoardVO vo) {
        System.out.println("===> JPA로 deleteBoard() 기능 처리");
        entityManager.remove(entityManager.find(BoardVO.class, vo.getSeq()));
    }

    public BoardVO getBoard(BoardVO vo) {
        System.out.println("===> JPA로 getBoard() 기능 처리");
        return (BoardVO) entityManager.find((BoardVO.class), vo.getSeq());
    }

    public List<BoardVO> getBoardList(BoardVO vo) {
        System.out.println("===> JPA로 getBoardList() 기능 처리");
        String condition = "";
        if(vo.getSearchCondition().equals("TITLE")) {
            condition = "title";
        } else if(vo.getSearchCondition().equals("CONTENT")) {
            condition = "content";
        }
        String jpql = "select b from BoardVO b where b." + condition + " like \'%" + vo.getSearchKeyword() +"%\' order by b.seq desc";
        return entityManager.createQuery(jpql).getResultList();
    }
}
