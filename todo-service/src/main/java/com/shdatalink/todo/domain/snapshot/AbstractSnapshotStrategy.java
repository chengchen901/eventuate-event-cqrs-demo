package com.shdatalink.todo.domain.snapshot;

import io.eventuate.*;
import io.eventuate.common.id.Int128;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
public abstract class AbstractSnapshotStrategy implements SnapshotStrategy {
    private int changeCount = 0;

    /**
     * 获取事件触发多少次才生成快照的阈值，避免每次触发快照影响性能
     */
    protected abstract int getSnapshotThreshold();
    protected abstract Snapshot generateSnapshot(Aggregate aggregate, Optional<Int128> snapshotVersion, List<EventWithMetadata> oldEvents, List<Event> newEvents);

    @Override
    public Optional<Snapshot> possiblySnapshot(Aggregate aggregate, Optional<Int128> snapshotVersion, List<EventWithMetadata> oldEvents, List<Event> newEvents) {
        changeCount += newEvents.size();
        if (changeCount < getSnapshotThreshold()) {
            // 未达到快照阈值，不生成快照
            log.info("AbstractSnapshotStrategy 执行 possiblySnapshot，未达到快照阈值:[{}]，不生成快照，Aggregate:{}", getSnapshotThreshold(), aggregate);
            return Optional.empty();
        }
        changeCount = 0;
        Snapshot snapshot = generateSnapshot(aggregate, snapshotVersion, oldEvents, newEvents);
        log.info("AbstractSnapshotStrategy 执行 possiblySnapshot，生成快照，Aggregate:{},Snapshot:{}", aggregate, snapshot);
        return Optional.of(snapshot);
    }


}
