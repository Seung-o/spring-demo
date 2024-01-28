package seungo.seungodemo.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import seungo.seungodemo.domain.Member;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("Seungo");

        Member result = repository.save(member);

        assertEquals(result.getName(), "Seungo");
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("Seungo1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Seungo2");
        repository.save(member2);

        Member member = repository.findByName("Seungo1").get();

        assertEquals(member.getName(), member1.getName());
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("Seungo1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Seungo2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertEquals(result.size(), 2);
    }
}
