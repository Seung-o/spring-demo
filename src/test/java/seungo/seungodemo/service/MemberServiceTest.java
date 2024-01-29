package seungo.seungodemo.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seungo.seungodemo.domain.Member;
import seungo.seungodemo.repository.MemoryMemberRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    MemoryMemberRepository memberRepository;
    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("Seungo");

        // when
        Long savedId = memberService.join(member);


        // then
        Member result = memberService.findOne(savedId).get();
        assertEquals(result.getName(), member.getName());
    }

    @Test
    void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("Seungo");
        Member member2 = new Member();
        member2.setName("Seungo");

        // when
        memberService.join(member1);
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        // then
        assertEquals(exception.getMessage(), "이미 존재하는 회원입니다.");
    }


}