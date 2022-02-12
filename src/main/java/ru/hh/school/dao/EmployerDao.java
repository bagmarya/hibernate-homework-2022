package ru.hh.school.dao;

import org.hibernate.SessionFactory;
import ru.hh.school.entity.Employer;
import ru.hh.school.entity.Vacancy;

import java.util.List;

public class EmployerDao extends GenericDao {

  public EmployerDao(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  /**
   * TODO: здесь нужен метод, позволяющий сразу загрузить вакасии, связанные с работодателем и в некоторых случаях
   * избежать org.hibernate.LazyInitializationException
   * Также в запрос должен передаваться параметр employerId
   * <p>
   * https://vladmihalcea.com/the-best-way-to-handle-the-lazyinitializationexception/
   * public Employer getEager(int employerId) {
   *     return getSession()
   *         .createQuery("from Employer employer", Employer.class)
   *         .getSingleResult();
   *   }
   */
  public Employer getEager(int employerId) {
    return getSession()
        .createQuery(
                "select e from Employer e left join fetch e.vacancies where e.id = :Id", Employer.class)
        .setParameter("Id", employerId)
        .getSingleResult();
  }

public Employer find(Integer id) {
  return getSession().find(Employer.class, id);
}

}
