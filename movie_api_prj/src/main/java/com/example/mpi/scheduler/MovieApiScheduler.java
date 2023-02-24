package com.example.mpi.scheduler;

import java.time.LocalDateTime;

import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.mpi.config.MovieApiJobConfig;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class MovieApiScheduler {
	
	private final MovieApiJobConfig movieApiJob;
	private final JobLauncher jobLauncher;
	
	// 영화 정보를 가져오는 잡 스케쥴러
//	@Scheduled(cron = "0 */5 * * * *")
	@Scheduled(cron = "0 2 0 * * ?")
	public void excuteMovieApiJob() {
		try {
			log.info("fetch movie api job start");
			jobLauncher.run(movieApiJob.movieApiJob(),
					new JobParametersBuilder()
					.addString("datetime", LocalDateTime.now().toString())
					.toJobParameters()
					);
			log.info("successfully complete job\n\n");
		} catch (JobExecutionException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	// 영화 장르를 가져오는 잡 스케쥴러
	@Scheduled(cron = "0 0 0 * * ?")
	public void excuteGenreCdApiJob() {
		try {
			log.info("fetch genre cd api job start");
			jobLauncher.run(movieApiJob.genreCdApiJob(),
					new JobParametersBuilder()
					.addString("datetime", LocalDateTime.now().toString())
					.toJobParameters()
					);
			log.info("successfully complete job\n\n");
		} catch (JobExecutionException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
