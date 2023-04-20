package programmers;

import org.junit.Test;

public class 헤비유저가소유한장소 {
  @Test
  public void test(){

  }

}
/**
 * 공간 임대 서비스
 * places 테이블 // 공간 정보
 *  id,
 *  name,
 *  host_id
 *
 * 공간을 둘 이상 가지면 헤비 유저
 *
 * 헤비 유저가 등록한 공간 정보를 아이디 순으로 뽑으시오
 * select p1.*
 * from
 *  places p1
 *  inner join (select host_id from places group by host_id having count(id) > 1) p2
 *  on p1.host_id = p2.host_id
 * order by id
 */
