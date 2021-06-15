package kai.sample.datasource.repo;

import kai.sample.datasource.entity.Coin;
import kai.sample.datasource.entity.CoinId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoinRepository extends JpaRepository<Coin, CoinId> {
    List<Coin> findByChartNameContains(String chartName);
    List<Coin> findByCodeContains(String code);
    List<Coin> findByChartNameContainsAndCodeContains(String chartName, String code);
}
