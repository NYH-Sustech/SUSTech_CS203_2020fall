# Data Structure & Algorithm Analysis

> 2020 fall

### 说明

本仓库包含了四个部分

#### Exam

包括了2019年的Quiz，期中考试试题和答案等

由于疏忽忘了下载2020的Quiz题...当我想整理的时候发现已经没有了...

#### Lab

包括了2020fall的大部分lab题，全部都是AC的题（没有AC的都没有放上来）

基本纯手写lab题，中难题查重率0%

**仅供参考，切勿直接照搬（毕竟还有很多写的很垃圾...）**

#### Lecture

包括了2020fall的所有Lecture ppt

#### Note

个人学习DSAA中复习时候记的笔记（大部分是把Lecture翻译成了中午...）

比较全，可以拿来看看复习复习

---

### 介绍

这是在2020年秋季学习的数据结构与算法分析课程

主课老师是唐博，Lab老师是沈昀

真的困死了每天早八的课爬起来上课，然后主课下课还得从一教跑到荔园上lab，考试还是早八

差点人都没了

---

### 学习心得

DSAA是非常耗时间的课程，主要是lab非常费时间写（如果你是纯手写，纯手造轮子而不借助CSDN，Github之类的东西的话），记得这里面LinkedList中的用链表实现数据流的中位数和将一串数维护成单调递减数列，两道题加起来估计写了超过24个小时（并且还有一题没写出来），整个人都在WA和TLE中崩溃了...

主要分享一些关于学习和写lab的心得，既是对自己这一个学期的总结，也是给后面的学弟学妹一点建议~

#### 主课学习

唐博老师虽然在某些地方非常欠，上课会讲骚话和段子之类的，浪费很多时间。但是整体上是非常优秀的老师，他的讲解还是很不错的，前期非常深入细节，只要用心听还是可以听得懂的。到后期因为课程进度赶快了上课有些内容就很多，需要自己课后回去再消化。

主课非常重要，**90%以上的考试题都来自/延申主课的ppt和老师留的一些遗留问题**，唐博的课**每次都会有签到（一次1分）**，所以也不能翘课。

对于主课知识点的学习需要课后多多**回去看ppt和查CSDN或者leet code官网**等，多加消化后会好很多。

DSAA这门课的**入门相对比较困难**，老师也总在前几节课劝退很多人，刚开始起步学习这些知识确实有些抽象，所以前期在知识点上可以多下功夫，不会的多跟同学讨论学习。

DSAA的核心知识点包括以下几个部分：

| 数据结构       | 核心知识点                                                   | 建议                                                         |
| -------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 基础概念与算法 | 时间、空间复杂度分析<br />Binary Search                      | **时间复杂度分析**是非常重要的环节，接下来的所有算法都需要有时间复杂度的分析<br />Binary Search理解起来很简单，会应用还需要多做题 |
| Array          | Insertion Sort<br />Selection Sort<br />Bubble Sort<br />Shell Sort<br />Merge Sort<br />Quick Sort | 前四种Sort比较简单<br />Merge Sort体现了**归并**的思想<br />Quick Sort体现了**递归**的思想<br />理解后面两种 |
| Linked List    | 基本操作<br />链表概念的理解                                 | 链表对于初学者比较抽象，但是理解它后后面的数据结构都很好理解了<br />**后面所有的数据结构基本都是基于链表实现的** |
| Stack & Queue  | 基本操作<br />栈和堆概念的理解                               | 栈和堆是个人认为数据结构里面最好理解的两个数据结构<br />多看看它们的应用 |
| String         | 基本操作<br />Rabin Karp<br />KMP                            | String是独立于其它数据结构的一个部分<br />两个核心算法都需要时间多理解，且**KMP的运用较多** |
| Tree           | 基本概念和操作<br />Binary Tree<br />Priority Queue（Heap）<br />Binary Search Tree<br />Balanced BST | Tree是高级数据结构的入门，它实现的**堆和BST都是高级的抽象**，需要课后多做题和看ppt理解<br />Tree的定义使它是**递归**的入门坎，学Tree的时候要多理解递归的思想 |
| Graph          | 基本概念和操作<br />图的遍历（BFS、DFS）<br />拓扑排序<br />最短路径算法（Dijkstra）<br />最小生成树（MST）<br />强连通图（SCC） | Graph是高级数据结构的进一步入门<br />其中它的后面的算法都涉及到了高级算法的初尝试，一定要在理解的基础上手写代码验证自己的理解效果<br />Graph的部分算法体现了**动归**的思想，也是动归的入门 |

#### Lab学习

lab写题前几件必须做的事情

- 用**快读快写板子（FastReadAndWrite）**
- 分析lab需要的时间（比如要求1s）以及Java的时间复杂度（1s可以进行$10^8$次运算）**推算时间复杂度**
- 数据范围，经常会有`int`被卡，必须要用`long`的情况**（巨坑，当你WA的时候请务必注意！）**



**当题写不出来的时候，不要灰心，也不要沮丧，忧郁的日子将会过去，相信吧，快乐的日子即将到来！**



lab的前面几题通常是对**课上PPT讲解的知识点和算法的实现**，通常是让你**手造轮子**，如果想要深入理解dsaa的课程，一定一定要手写这些板子题，最好不要去找别人写好的板子。

lab的后面几题通常是对于**算法的运用**，这些题在考试中就可能会作为一道算法题考你，这些题不一定要非得自己想想出来，可以跟同学讨论讨论或者去查一查，但是可以多总结一些这些算法的实际运用题，并且在考试的时候写在Cheating paper上（印象深刻的比如有漆狗屋-最大最小，数据流的中位数，每日温度之类的）。



**最关键的事情是，不要完全抄同学的，查重0分**

lab是对你的代码能力的一次提升，因为每周都会花大于8小时的时间来写，会对你的代码能力进行全面拓展！

加油！挺过去，你就翻过这座山理解了DSAA了

#### Quiz学习

**考试允许带纯手写Cheating Paper**

Quiz的考试在唐博的课上一般是这样的

- **Quiz1：非常难，平均分不及格**
- **Midterm：中等难度，平均分60-70，认真学习可以到80-90**
- **Quiz2：非常简单，平均分90+**
- **Final：中等偏难，题量大，平均分60-70**

但是唐博他会给一堆Bonus啊**（Bonus lab、校赛、签到、考试...）**数不清的Bonus会弥补考试考的差，最终还是会有一个不错的分数的。

复习着重于以下几个部分：

- ppt上的**所有基础知识点（占考试的60%以上）**
- 上课唐博的**遗留问题**，可以回去多思考一下（可能占考试的10%）
- 每次**lab后面几道题的思想**（不需要你会写代码，只要会算法的思想即可）（可能占考试的15-20%）
- 可以去CSDN和leetcode上学习一些算法的**常见运用和骚操作**，把它们理解一下记在Cheating paper上（比如学习栈和队列的时候，可以查栈和队列的经典运用，学习图的时候可以查DFS的运用等等），你会收获到很多骚操作的（可能占考试的15-20%）

其它的复习可以去网上（特指Google）手动搜索一些别的学校的期中期末考试资料，自行选择一些有参考价值的做就可以啦~

### 学习参考网站

SUSTech Mooc：https://visualgo.net/zh

中国大学Mooc：https://www.icourse163.org/

Bilibili：https://www.bilibili.com/

CSDN：https://www.csdn.net/

Github：https://github.com/

LeetCode：https://leetcode.com/

SUSTech OJ：https://acm.sustech.edu.cn/onlinejudge/

算法可视化：https://visualgo.net/zh



