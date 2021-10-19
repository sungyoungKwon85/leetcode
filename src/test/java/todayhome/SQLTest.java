package todayhome;

public class SQLTest {
    /**
     * -- insert into members(name) values('kim'); oo
     * -- insert into orders(member_id, title) values(1, 'desk');
     * -- insert into points(member_id, `point`, reason, used_point, expired_at) values(1, 1000, '첫사진업로드', 0, '2021-11-30');
     * -- insert into options(option_name) values('desk2');
     * -- insert into order_options(order_id, option_id, option_name, unit_price, qty, amount) values(4, 3, 'desk1', 300, 2, 600);
     * -- insert into payments(order_option_id, payment_method, price) values(5, 'card', 600);
     * -- insert into points(member_id, `point`, reason, used_point, expired_at ) values(1, 800, '구매', 200, '2021-10-30');
     *
     * -- 전체 주문 리스트 가져오기
     * -- 포인트를 사용했는지 여부 추가하기
     * -- select
     * -- 	o.id as order_id,
     * -- 	m.name as name,
     * --     if (op.order_id is null, 0 , 1)  as is_point
     * -- from
     * -- 	orders o
     * --     left outer join (
     * -- 		select
     * -- 			distinct oo.order_id
     * -- 		from
     * -- 			order_options oo
     * -- 			left outer join payments p on oo.id = p.order_option_id
     * -- 		where
     * -- 			p.payment_method = 'point'
     * -- 	) op on o.id = op.order_id
     * --     left outer join members m on o.member_id = m.id
     * -- ;
     *
     * -- 전체 주문리스트, 사용자별 2020년 12월 한달간 1회 주문시 평균 구매 금액이 높며 주문횟수가 낮은 순으로 정렬, 평균 구매 금액은 가장 가까운 정수로 반올림
     * -- select
     * -- 	m.name as member_name,
     * --     sum(ovg.avg_amount) as avg_amount,
     * --     round(count(ovg.order_id)) as order_count
     * -- from
     * -- 	orders o
     * --     left outer join members m on o.member_id = m.id
     * --     left outer join
     * -- 	(select
     * -- 		oo.order_id as order_id,
     * -- 		avg(oo.amount) as avg_amount
     * -- 	from
     * -- 		order_options oo
     * -- 	where
     * -- 		oo.created_at >= '2020-12-01' and oo.created_at <= '2020-12-31'
     * -- 	group by oo.order_id
     * -- 	) ovg on o.id = ovg.order_id
     * -- group by o.member_id
     * -- order by avg_amount desc, order_count asc
     * -- ;
     *
     *
     * -- 2021-01-01 기준 2020년 사용자에게 지급된 포인트 총액, 사용금액, 미사용된 금액, 유효기간이 종료된 금액을 각 월별로 추출 및 월별 정렬
     * -- create table month_2020 (
     * -- 	month varchar(45)
     * -- );
     * -- insert into month_2020(month) values('01');
     * -- insert into month_2020(month) values('02');
     * -- insert into month_2020(month) values('03');
     * -- insert into month_2020(month) values('04');
     * -- insert into month_2020(month) values('05');
     * -- insert into month_2020(month) values('06');
     * -- insert into month_2020(month) values('07');
     * -- insert into month_2020(month) values('08');
     * -- insert into month_2020(month) values('09');
     * -- insert into month_2020(month) values('10');
     * -- insert into month_2020(month) values('11');
     * -- insert into month_2020(month) values('12');
     *
     *
     * -- select
     * -- 	m2.`month` as `month`
     * -- 	, sum(pp1.`point`) as amount
     * -- 	, sum(pp1.used_point) as used
     * -- 	, sum(pp1.`point` - pp1.used_point) as unused
     * -- 	, sum(pp2.`point` - pp2.used_point) as expired
     * -- from
     * -- 	month_2020 m2
     * --     left outer join (
     * --     select * from points p where p.created_at >= '2020-01-01' and p.created_at <= '2020-12-31'
     * --     ) pp1 on m2.`month` = month(pp1.created_at)
     * --     left outer join (
     * --     select * from points p where p.expired_at >= '2020-01-01' and p.expired_at <= '2020-12-31'
     * --     ) pp2 on m2.`month` = month(pp2.expired_at)
     * -- group by `month`
     * -- ;
     *
     *
     *
     * -- 구매실적에 따라 회원 등급 나눔
     * select
     * 	m.id as id
     * 	, m.name as `name`
     *     , count(ovg.order_id) as order_count
     *     , sum(ovg.avg_amount) as total_order_amount
     *     , case
     * 		when count(ovg.order_id) >= 4 and sum(ovg.avg_amount) >= 1000000 then 'Platinum'
     *         when count(ovg.order_id) = 3 and sum(ovg.avg_amount) >= 500000 then 'VIP'
     *         when count(ovg.order_id) = 2 and sum(ovg.avg_amount) >= 300000 then 'Friend'
     *         else 'Normal'
     * 	  end as rating
     * from
     * 	orders o
     *     left outer join members m on o.member_id = m.id
     *     left outer join
     * 	(select
     * 		oo.order_id as order_id,
     * 		avg(oo.amount) as avg_amount
     * 	from
     * 		order_options oo
     * 	where
     * 		oo.created_at >= '2020-07-01' and oo.created_at <= '2021-01-01'
     * 	group by oo.order_id
     * 	) ovg on o.id = ovg.order_id
     * group by id
     * ;
     */

}
